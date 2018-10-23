# -*- coding: utf-8 -*-

import sys, subprocess
import argparse
import os
from subprocess import PIPE


parser = argparse.ArgumentParser(description="Calculate graph edit distance between two Java programs.")
parser.add_argument("jarDir", help="Dictionary that contains jar files.")
parser.add_argument("outputDir", help="Dictionary that contains output filesß.")

args = parser.parse_args()

def execute(commands):
  p = subprocess.Popen(commands, stdout=PIPE, stderr=PIPE)
  (out, err) = p.communicate()
  if p.returncode != 0:
    raise Exception("EXecution {0} fail".format(' '.join(commands)))
  return out


def gen_call_graph_helper(jar_file, out_file):
  out = execute(["java", "-jar", "libs/javacg-0.1-SNAPSHOT-static.jar", jar_file])
  res = bytes.decode(out).split('\n')
  with open(out_file, 'w') as fw:
    for line in res:
        if line[:2] != 'C:':
          edges = line.split(' ')
          if len(edges) == 2:
            edges[0] = edges[0][:edges[0].rfind('(')]
            edges[0] = edges[0][edges[0].rfind('.') + 1:]

            edges[1] = edges[1][:edges[1].rfind('(')]
            edges[1] = edges[1][edges[1].rfind('.') + 1:]

            fw.write(edges[0] + ' ' + edges[1] + '\n')
    fw.close()
  return res

def get_data_list(file_dir):
  data_list=[]
  for root, dirs, files in os.walk(file_dir):
    for file in files:
      if os.path.splitext(file)[1] == '.jar':
        data_list.append(os.path.splitext(file)[0])
  return data_list

def make_dir(path):
  path = path.strip(' ')
  path = path.rstrip('\\')

  if not os.path.exists(path):
    os.makedirs(path)
data_list = get_data_list(args.jarDir)
print(data_list)
make_dir(args.outputDir)
for data in data_list:
  gen_call_graph_helper(args.jarDir + '/' + data + '.jar', args.outputDir + '/' + data + '.txt')
