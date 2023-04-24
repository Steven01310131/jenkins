
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
                    sh 'mvm build-helper:parse-version version:set \\
                    -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\{parsedVersion.nextIncrementalVersion} \\
                    version:commit'
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
}
}
