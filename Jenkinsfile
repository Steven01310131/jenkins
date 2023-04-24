def gv

pipeline {
    agent any
        tools {
        maven 'Maven'
    }

    stages {
        stage("init") {
            steps {
                script {
                    echo "building app"
                    sh 'mvn package'
                }
            }
        }
        stage("build jar") {
            steps {
                script {
                    echo "building jar"
                    gv.buildApp()
                }
            }
        }
        stage("build image") {
            steps {
                script {
                    echo "building docker image"
                    withCredentials([usernamePassword(credentialsId: 'a4', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                    sh 'docker build -t nanajanashia/demo-app:jma-2.0 .'
                    sh "echo $PASS | docker login -u $USER --password-stdin"
                    sh 'docker push steven01310131/a4:jma-2.0'

                }
            }
        }

    }   
}
