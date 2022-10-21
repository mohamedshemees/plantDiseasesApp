import copy
import warnings
warnings.filterwarnings('ignore')
import os
import cv2
import keras
from tensorflow.keras import backend as K
from keras.preprocessing.image import ImageDataGenerator
from tensorflow.keras.utils import load_img, img_to_array
#from keras.applications.resnet50 import preprocess_input, ResNet50
from tensorflow.keras.applications.resnet50 import preprocess_input
import numpy as np  
import matplotlib.pyplot as plt  
import seaborn as sns
from sklearn.utils import shuffle
from sklearn.metrics import confusion_matrix
from sklearn.model_selection import train_test_split
import tensorflow as tf
from tensorflow.keras import models , layers

tf.compat.v1.disable_eager_execution()


W = 256 
H = 256 
label_to_class = {
    'Brown rust':  0,
    'Healthy Wheat':  1,
    'powdery_mildew':  2,
    'yellow_rust':3
}
class_to_label = {v: k for k, v in label_to_class.items()}
n_classes = len(label_to_class)
print(len(label_to_class))
def get_images(dir_name='G:\AllWheatData\TestLast\Data-less-diseases', label_to_class=label_to_class):
    """read images / labels from directory"""
    
    Images = []
    Classes = []
    
    for label_name in os.listdir(dir_name):
        cls = label_to_class[label_name]
        
        for img_name in os.listdir('/'.join([dir_name, label_name])):
            img = load_img('/'.join([dir_name, label_name, img_name]), target_size=(W, H))
            img = img_to_array(img)
            
            Images.append(img)
            Classes.append(cls)
            
    Images = np.array(Images, dtype=np.float32)
    Classes = np.array(Classes, dtype=np.float32)
    Images, Classes = shuffle(Images, Classes, random_state=0)
    
    return Images, Classes

Images, Classes = get_images()

Images.shape, Classes.shape



n_total_images = Images.shape[0]


indices_train, indices_test = train_test_split(list(range(Images.shape[0])), train_size=0.8, test_size=0.2, shuffle=False)

x_train = Images[indices_train]
y_train = Classes[indices_train]
x_test = Images[indices_test]
y_test = Classes[indices_test]

x_train.shape, y_train.shape, x_test.shape, y_test.shape




y_train = keras.utils.to_categorical(y_train, n_classes)
y_test = keras.utils.to_categorical(y_test, n_classes)

y_train.shape, y_test.shape





datagen_train = ImageDataGenerator(
    preprocessing_function=preprocess_input, # image preprocessing 
    rescale=1./255,
    rotation_range=10,
    horizontal_flip=True,
    #rotation_range=30,                       # randomly rotate images in the range
    zoom_range=0.1,                          # Randomly zoom image
    #width_shift_range=0.1,                   # randomly shift images horizontally
    #height_shift_range=0.1,                  # randomly shift images vertically
    #horizontal_flip=True,                    # randomly flip images horizontally
    #vertical_flip=False,                     # randomly flip images vertically
)
datagen_test = ImageDataGenerator(
    preprocessing_function=preprocess_input,
    rescale=1./255,
    rotation_range=10,
    horizontal_flip=True,# image preprocessing function
)


IMAGE_SIZE=256
BATCH_SIZE=32
CHANNELS=3

input_shape = ( IMAGE_SIZE, IMAGE_SIZE, CHANNELS)
n_classes = 4
inputshape = x_train.shape[1:]

def build_model():
    """build model function"""
           
    # model
    model = models.Sequential([                     
        layers.Conv2D(32,  (3,3), activation='relu', input_shape=[W, H, 3]),
        layers.MaxPooling2D((2, 2)),
        layers.Conv2D(64,  (3,3), activation='relu'),
        layers.MaxPooling2D((2, 2)),
        layers.Dropout(.25),
        layers.Conv2D(64,   (3,3), activation='relu'),
        layers.MaxPooling2D((2, 2)),
        layers.Dropout(.25),
        layers.Conv2D(64, (3, 3), activation='relu'),
        layers.MaxPooling2D((2, 2)),
        layers.Dropout(.25),
        layers.Conv2D(64, (3, 3), activation='relu'),
        layers.MaxPooling2D((2, 2)),
        layers.Dropout(.25),
        layers.Conv2D(64, (3, 3), activation='relu'),
        layers.MaxPooling2D((2, 2)),
        layers.Dropout(.25),
        #layers.Flatten(),
        layers.Dense(64, activation='relu'),       
        layers.GlobalAveragePooling2D(),
        layers.Dense(n_classes, activation='softmax'),
    ])
    
    # frozen weights
    '''
    for layer in model.layers[:-10]:
        layer.trainable = False or isinstance(layer, BatchNormalization) # If Batch Normalization layer, it should be trainable
        '''
    # compile
    model.compile(optimizer='adam',
                  loss='categorical_crossentropy',                 
                  metrics=['accuracy'])
    
    return model




model =build_model()

model.summary()


history = model.fit_generator(
    datagen_train.flow(x_train, y_train, batch_size=16),
    epochs=50,
    validation_data=datagen_test.flow(x_test, y_test, batch_size=16),
)


## plot confusion matrix

x = preprocess_input(copy.deepcopy(x_test))
y_preds = model.predict(x)
y_preds = np.argmax(y_preds, axis=1)
y_trues = np.argmax(y_test, axis=1)
cm = confusion_matrix(y_trues, y_preds)

fig, ax = plt.subplots(figsize=(7, 6))

sns.heatmap(cm, annot=True, fmt='d', cmap='Blues', cbar_kws={'shrink': .3}, linewidths=.1, ax=ax)

ax.set(
    xticklabels=list(label_to_class.keys()),
    yticklabels=list(label_to_class.keys()),
    title='confusion matrix',
    ylabel='True label',
    xlabel='Predicted label'
)
params = dict(rotation=45, ha='center', rotation_mode='anchor')
plt.setp(ax.get_yticklabels(), **params)
plt.setp(ax.get_xticklabels(), **params)
plt.show()



model.call = tf.function(model.call)



loadedmodel=keras.models.load_model('Wheat 10 21 last.h5')

img = load_img('rusty.jpg', target_size=(W, H))
img = img_to_array(img)

def superimpose(img, cam):
    """superimpose original image and cam heatmap"""
    
    heatmap = cv2.resize(cam, (img.shape[1], img.shape[0]))
    heatmap = np.uint8(255 * heatmap)
    heatmap = cv2.applyColorMap(heatmap, cv2.COLORMAP_JET)

    superimposed_img = heatmap  + img 
    superimposed_img = np.minimum(superimposed_img, 255.0).astype(np.uint8)  # scale 0 to 255  
    superimposed_img = cv2.cvtColor(superimposed_img, cv2.COLOR_BGR2RGB)
    
    return img, heatmap, superimposed_img

def _plot(model, cam_func, img, cls_true):
    """plot original image, heatmap from cam and superimpose image"""
    
    # for cam
    x = np.expand_dims(img, axis=0)
    x = preprocess_input(copy.deepcopy(x))

    # for superimpose
    img = np.uint8(img)

    # cam / superimpose
    cls_pred, cam = cam_func(model=model, x=x, layer_name=model.layers[-6].name)
    img, heatmap, superimposed_img = superimpose(img, cam)

    fig, axs = plt.subplots(ncols=3, figsize=(9, 4))

    axs[0].imshow(img)
    axs[0].set_title('original image')
    axs[0].axis('off')

    axs[1].imshow(heatmap)
    axs[1].set_title('heatmap')
    axs[1].axis('off')

    axs[2].imshow(superimposed_img)
    axs[2].set_title('superimposed image')
    axs[2].axis('off')

    plt.suptitle('True label: ' + class_to_label[cls_true] + ' / Predicted label : ' + class_to_label[cls_pred])
    plt.tight_layout()
    plt.show()


def grad_cam(model, x, layer_name):
    """Grad-CAM function"""
    
    cls = np.argmax(model.predict(x))
    
    y_c = model.output[0, cls]
    conv_output = model.get_layer(layer_name).output
    grads = K.gradients(y_c, conv_output)[0]    # Get outputs and grads
    gradient_function = K.function([model.input], [conv_output, grads])
    output, grads_val = gradient_function([x])
    print(type(cls))
    print(type(conv_output))
    print(type(grads))
    print(type(gradient_function))
    print(type(output))
    print(type(grads_val).shape)
    print((grads_val).shape)

    print(type(y_c))

    
    output, grads_val = output[0, :], grads_val[0,:,:,:]
    
    weights = np.mean(grads_val, axis=(0, 1)) # Passing through GlobalAveragePooling

    cam = np.dot(output, weights) # multiply
    cam = np.maximum(cam, 0)      # Passing through ReLU
    cam /= np.max(cam)            # scale 0 to 1.0

    return cls, cam

_plot(model=loadedmodel, cam_func=grad_cam, img=img, cls_true=Classes[0])



model.layers[-5].name

cls = np.argmax(model.predict(x))




model.save("WheatGradCam.h5")







'''

for images_batch, labels_batch in test_ds.take(130):
    
    first_image = images_batch[4].numpy().astype('uint8')
    first_label = labels_batch[4].numpy()
    
    print("first image to predict")
    plt.imshow(first_image)
    
    print("actual label:",class_names[first_label])
    
    batch_prediction = model.predict(images_batch)
    print("predicted label:",class_names[np.argmax(batch_prediction[4])])

'''