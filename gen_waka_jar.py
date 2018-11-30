import os

source_jar_dir = 'dist/'
target_jar_dir = 'jars/'
cur_head_hash = 'ef33931ad39c181093257fa58e0d086ab0d85f76'
cmd = 'git reset --hard ' + cur_head_hash
os.system(cmd)

if not os.path.exists(target_jar_dir):
    os.makedirs(target_jar_dir)


for i in range(200):
	cmd = 'git reset --hard HEAD^'
	os.system(cmd)
	cmd = 'mvn clean install -Dmaven.test.skip=true'
	os.system(cmd)

	files = os.listdir(source_jar_dir)
	for f in files:
		if f.split('-')[-1] == 'SNAPSHOT.jar':
			cmd = 'mv ' + os.path.join(source_jar_dir, f) + ' ' + os.path.join(target_jar_dir, 'weka' + str(i) + '.jar')
			os.system(cmd)
			break
