pipeline{
    agent any
    stages{
        stage("checkout"){
            steps{
                git "https://github.com/shubh1sinha/tour-management.git"
            }
        }
        
         stage("package"){
            steps{
            sh "mvn clean package"
            }
        }
        stage("dockerize"){
            steps{
            sh "sudo docker-compose up"
            }
        }
    }
 }
