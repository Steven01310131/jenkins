def gv

pipeline {
    agent any
    stages {
        stage("init") {
            steps {
                script {
                    gv = load "script.groovy"
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
                    echo "building image"
                    gv.testApp()
                }
            }
        }
        stage("deploy") {
            input{
                message "Select the enviroment"
                ok "Done"
                parameters{
                    choice(name: "ENV",choices:["dev","staging"],description:"")
                }
            }
            steps {
                script {
                    echo "deploying"
                    gv.deployApp()
                    echo "deploy to ${ENV}"
                }
            }
        }
    }   
}
