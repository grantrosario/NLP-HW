import nltk
import string
import os
import numpy as np
import random
import re

train = open("doc_test.txt")
matrix = open("WD_matrix_test.txt", "w")

train = train.read()

doc_count = 0

labels = ["student", "project", "course", "faculty"]
matrix.write("Doc#" + "\t")
for label in labels:
	matrix.write(label + "\t")
matrix.write("\n")

docs = re.split('\n', train)
for doc in docs:
	doc_count += 1
	words = nltk.tokenize.word_tokenize(doc)
	student_in = 0
	course_in = 0
	project_in = 0
	faculty_in = 0
	for word in words:
		if word == "student":
			student_in = 1
			continue
		if word == "project":
			project_in = 1
			continue
		if word == "course":
			course_in = 1
			continue
		if word == "faculty":
			faculty_in = 1
			continue

	matrix.write('{}:\t{}\t{}\t{}\t{}\n'.format(doc_count, student_in, course_in, project_in, faculty_in))

	train.close()
	matrix.close()