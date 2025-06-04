# 📦 Set up environment variables

> Workflow that set up the environment variables such as branch , env and tag based on automatic or manual procedure.

## 🚀 Features

-Environment , branch and tag are going to be set up according to sthe following scenarios : 

Scenario: Calculating the environment and branch automatically
             When the code into the <branch> is merged
             Then the <branch> is taken from github and set as a BRANCH variable with the value
              And the <environment> is calculated automatically according to the <branch>
        Examples:
                  | environment | branch  |
                  | develop     | develop |
                  | qa          | master  |
        
Scenario: Calculating the environment manually and setting the branch automatically
            Given the user is executing the workflow manually
              And the user provides one of the following <environments> as input
                  | environment |
                  | develop     |
                  | qa          |
             When the user hits the button start
             Then the workflow identifies that the action was manual
              And sets the <environment> value in the workflow
              And sets the <branch> value in the workflow
        Examples:
                  | environment | branch  |
                  | develop     | develop |
                  | qa          | master  |
            
            
Scenario: Setting the environment and branch manually
            Given that the environment and tag were set manually
             When the user hits the button start
             Then the workflow identifies that the action was manually added
              And sets the <environment> value in the workflow
              And sets the <branch> value in the workflow
        Examples:
                  | environment | tag    |
                  | stg         | 1.1.0  |
                  | LT          | hotfix |
                  | STG         | 1.1.0  |

## 💡 Usage

Example usage:

```bash
# For Python
python main.py

# For Node.js
npm start
```

Or as a library in your own code:

```python
from project_name import do_something
do_something()
```

## 📷 Screenshots

_Add a few screenshots or a short demo video/gif here to illustrate functionality._
