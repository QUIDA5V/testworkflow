#!/bin/bash
set -euo pipefail

file_path=$1     # Ruta al kustomization.yaml en el repo destino
image_name=$2    # Nombre de la imagen
image_tag=$3     # Tag nuevo

echo "➡️ Updating kustomization file: $file_path"
echo "➡️ Setting image: $image_name:$image_tag"

# Ir a la carpeta donde está el archivo
cd "$(dirname "$file_path")"

# Actualizar imagen con kustomize
kustomize edit set image "$image_name=$image_name:$image_tag"

echo "✅ kustomization.yaml updated successfully"
