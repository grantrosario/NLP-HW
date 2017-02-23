import sys
import nltk
import string
import os
import numpy as np
import random
import re

#link to the svm and svmutil libraries in libvsm folder
sys.path.append('/Users/grantrosario/Downloads/libsvm-3.22/python' )
import svm
from svmutil import *

y, x = svm_read_problem('libsvm_train.txt')
model = svm_train(y, x, '-t 0')
y, x = svm_read_problem('libsvm_test.txt')
p_label, p_acc, p_val = svm_predict(y, x, model)
print("\n{}\n{}\n\n{}".format(p_label, p_acc, p_val))
