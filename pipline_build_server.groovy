pipeline
{
    parameters
    {
        string defaultValue: 'http://repositories.actionsquare.corp/svn/b2r/trunk/BladeRA/Engine', description: '浚柳 家胶内靛 GIT 林家', name: 'ENGINE_SVN_URL', trim: false
        string defaultValue: 'HEAD', description: 'Engine svn revision id', name: 'ENGINE_REVISION_ID', trim: false
        string defaultValue: 'http://repositories.actionsquare.corp/svn/b2r/trunk/BladeRA/Content/BladeRA', description: '府家胶 SVN 林家', name: 'GAME_SVN_URL', trim: false
        string defaultValue: 'HEAD', description: 'Game svn revision id', name: 'GAME_REVISION_ID', trim: false
        string defaultValue: 'http://repositories.actionsquare.corp/svn/b2r/trunk/BladeRA/Client/Source', description: '霸烙 家胶内靛 SVN 林家', name: 'GAME_SRC_SVN_URL', trim: false
        string defaultValue: 'HEAD', description: 'Game svn revision id', name: 'GAME_SRC_REVISION_ID', trim: false
        booleanParam defaultValue: false, description: '府呼靛咯何', name: 'REBUILD_ENGINE'
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