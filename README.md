# 📦 Custom state-file update

> This is a custom action that allows us to perform the image tag name update in a state-file repo

## 🚀 Inputs required

- image_tag_name : Name of the new tag name

- image_name: Name of the image to be updated

- repo_name: Name of the state-file repo

- organization_workspace: Name of the workspace's organization

- src_file: Direcotry address of the state file (.yaml)

- bitbucket_username: Username of the bitbucket account

- bitbucket_user_email: Email of the bitbucket account

- bitbucket_app_pwd: Password of the application , usually stored as an environment key in the Git account

## 💡 Usage

Example usage:

When this action is called with all the inputs required then the file (.yaml) with be updated with the new tag name
associated to an image for instance

-----------.yaml file----------------
apiVersion: kustomize.config.k8s.io/v1beta1
kind: Kustomization

# Set namespace in all files

namespace: namespace-dev

# Set image tags for deployment

images:

- name: gcr.io/myimage
  newTag: v.10
