pipeline{
    agent any
    stages{
        stage("checkout"){
            steps{
                git "https://github.com/shubh1sinha/tour-management-application.git"
            }
        }
        
         stage("package"){
            steps{
            sh "mvn clean package"
            }
        }
    }
 }