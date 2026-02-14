# CI Workflow Documentation

## Overview

This repository uses GitHub Actions to automatically build release mod JAR files for all three supported mod loaders: Fabric, Forge, and NeoForge.

## Workflow Triggers

The CI workflow (`.github/workflows/build.yml`) is triggered by:

1. **Push to main/master branch** - Builds and uploads artifacts for testing
2. **Pull Requests** - Validates that the code builds correctly
3. **Version Tags** (e.g., `v1.5.3`) - Builds and creates a GitHub Release with all JAR files
4. **Manual Trigger** - Can be run manually from the Actions tab

## Build Process

### Build Job

The build job uses a matrix strategy to build all three loader variants in parallel:

- **Fabric** - Built using Fabric Loom
- **Forge** - Built using ForgeGradle
- **NeoForge** - Built using NeoGradle

Each build:
1. Sets up JDK 21 (Temurin distribution)
2. Caches Gradle dependencies for faster builds
3. Runs `./gradlew :<Loader>:build`
4. Uploads the resulting JAR files as artifacts

### Release Job

When a version tag is pushed (e.g., `v1.5.3`), an additional release job:
1. Downloads all build artifacts from the build job
2. Creates a GitHub Release with the tag name
3. Attaches all JAR files to the release

## Security

The workflow uses minimal permissions following the principle of least privilege:
- **Build job**: `contents: read` - Only needs to read the repository
- **Release job**: `contents: write` - Needs to create releases and upload assets

## Manual Build

To trigger a build manually:
1. Go to the "Actions" tab in GitHub
2. Select "Build Release Mod JARs" workflow
3. Click "Run workflow"
4. Select the branch and click "Run workflow"

## Creating a Release

To create a new release:
1. Update the version in `gradle.properties` (e.g., `mod_version=1.5.4`)
2. Commit and push the changes
3. Create and push a git tag:
   ```bash
   git tag v1.5.4
   git push origin v1.5.4
   ```
4. The CI will automatically build all variants and create a GitHub Release

## Artifacts

Build artifacts are available for download from the Actions tab for 90 days. Each build produces:
- `Fabric-build` - Contains the Fabric mod JAR
- `Forge-build` - Contains the Forge mod JAR
- `NeoForge-build` - Contains the NeoForge mod JAR

## Troubleshooting

If a build fails:
1. Check the Actions tab for detailed logs
2. Common issues:
   - Missing dependencies - Ensure all dependencies are accessible
   - Build errors - Check that the code compiles locally
   - Java version mismatch - The workflow uses Java 21

## Local Testing

To test builds locally before pushing:

```bash
# Build all loaders
./gradlew build

# Build specific loader
./gradlew :Fabric:build
./gradlew :Forge:build
./gradlew :NeoForge:build
```
