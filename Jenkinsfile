pipeline{
    agent any
    stages{
        stage("checkout"){
            steps{
                git clone "https://github.com/shubh1sinha/tour-management.git"
            }
        }
        
         stage("package"){
            steps{
            sh "mvn clean package"
            }
        }
        
        stage("dockerize"){
            steps{
            sh "docker-compose up"
            }
        }
    }
 }