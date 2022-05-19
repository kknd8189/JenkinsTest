pipeline
{
    agent
    {
        node
        {
            label 'TEST_LABEL'
        }
    }
    stages 
    { 
        stage('Example') 
        {
            steps 
            {
                echo 'Hello World'
            }
        }
    }
}