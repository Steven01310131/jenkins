
pipeline {
    agent any
        tools {
        maven 'Maven'
    }

    stages {
        stage('increment version'){
            steps{
                script{
                    echo 'incrementing app version'
                    sh 'mvn build-helper:parse-version versions:set \
                        -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} \
                        versions:commit'

                    def matcher=readFile('pom.xml')=~'<version>(.+)</version'
                    def version=matcher[0][1]
                    env.IMAGE_NAME="$version-$BUILD_NUMBER"
                }
            }
        }
        stage("build app") {
            steps {
                script {
                    echo "building app"
                    sh 'mvn clean package'
                }
            }
        }
        stage("build image") {
            steps {
                script {
                    echo "building docker image"
                    withCredentials([usernamePassword(credentialsId: 'a4', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                    sh 'docker images'
                    sh "docker build -t steven01310131/a4:${IMAGE_NAME} ."
                    sh "echo $PASS | docker login -u $USER --password-stdin"
                    sh "docker push steven01310131/a4:${IMAGE_NAME}"

                }
            }
        }

    }
        stage('commit version update') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'github_token', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                        // git config here for the first time run
                        sh 'git config --global user.email "jenkins@example.com"'
                        sh 'git config --global user.name "jenkins"'

                        sh "git remote set-url origin https://${USER}:${PASS}@github.com/Steven01310131/jenkins.git"
                        sh 'git add .'
                        sh 'git commit -m "ci: version bump"'
                        sh 'git push origin HEAD:jenkins'
                    }
                }
            }
   
        }
    }
}
