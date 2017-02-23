import nltk
import string
import os
import numpy as np
import random
import re

file = open("doc_train.txt")
matrix = open("libsvm_train.txt", "w")
full_txt = file.read()

#labels = ["student", "faculty", "project", "course"]
def writeToFile(class_num, class_name, class_in=0):
	matrix.write(class_num + " ")
	docs = re.split('\n', full_txt)
	doc_count = 0
	for doc in docs:
		doc_count += 1
		words = nltk.tokenize.word_tokenize(doc)
		class_in = 0
		for word in words:
			if word == class_name:
				class_in = 1
				matrix.write('{}:{} '.format(doc_count, class_in))
				break
			else:
				continue
	matrix.write('\n')

writeToFile("1", "student", 0)
writeToFile("2", "faculty", 0)
writeToFile("3", "project", 0)
writeToFile("4", "course", 0)