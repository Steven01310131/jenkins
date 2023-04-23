
pipeline {
    agent any 
    parameters{
        choice(name:'VERSION', choices:['1.1.0','1.2.0','1.3.0'],description:'')
        booleanParam(name: 'executeTests',defaultValue: true, description: '')
    }
    stages {
        stage("init"){
            steps{
                script{
                    gv=load"script.groovy"
                }
            }
        }
        stage("build"){
            steps {   
                script{
                    gv.buildApp()
                } 
            }
        }
        stage("test"){
            when{//when should this stage execute
                expression{
                    params.executeTests
            }
            steps {   
                script{
                    gv.testApp()
                } 
            }
        }
        stage("deploy"){
            steps {   
                script{
                    gv.deployApp()
                } 
            }
        }
    }
    post { //logic to performed after the stages

        always{// as the name implies

        }
        success{

        }
        failure{

        }

    }
}