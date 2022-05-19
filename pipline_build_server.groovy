pipeline
{
    parameters
    {
        string defaultValue: 'http://repositories.actionsquare.corp/svn/b2r/trunk/BladeRA/Engine', description: '���� �ҽ��ڵ� GIT �ּ�', name: 'ENGINE_SVN_URL', trim: false
        string defaultValue: 'HEAD', description: 'Engine svn revision id', name: 'ENGINE_REVISION_ID', trim: false
        string defaultValue: 'http://repositories.actionsquare.corp/svn/b2r/trunk/BladeRA/Content/BladeRA', description: '���ҽ� SVN �ּ�', name: 'GAME_SVN_URL', trim: false
        string defaultValue: 'HEAD', description: 'Game svn revision id', name: 'GAME_REVISION_ID', trim: false
        string defaultValue: 'http://repositories.actionsquare.corp/svn/b2r/trunk/BladeRA/Client/Source', description: '���� �ҽ��ڵ� SVN �ּ�', name: 'GAME_SRC_SVN_URL', trim: false
        string defaultValue: 'HEAD', description: 'Game svn revision id', name: 'GAME_SRC_REVISION_ID', trim: false
        booleanParam defaultValue: false, description: '�����忩��', name: 'REBUILD_ENGINE'
    }

    agent
    {
        node
        {
            label 'TEST_LABEL'
        }
    }


    stages 
    { 
        stage('initial') 
        {
            steps 
            {
                echo 'Hello World'
            }
        }

        stage('stage1') 
        {
            steps 
            {
                echo 'Hello World'
            }
        }

    }
}