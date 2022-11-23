pipeline{
    agent any
    stages{
        stage("checkout"){
            steps{
                bat "git clone https://github.com/shubh1sinha/tour-management.git"
            }
        }
        
         stage("package"){
            steps{
            bat "mvn clean package -DskipTests"
            }
        }
        
        stage("dockerize"){
            steps{
            bat "docker-compose up"
            }
        }
    }
 }