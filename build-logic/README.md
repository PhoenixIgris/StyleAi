## `:build-logic`

Contains all the build logics

## Integration

# Ios Integration
 - Change run script to(navigation: Targets --> Build Phases --> Run Script)
   
    if [ "YES" = "$OVERRIDE_KOTLIN_BUILD_IDE_SUPPORTED" ]; then
   echo "Skipping Gradle build task invocation due to OVERRIDE_KOTLIN_BUILD_IDE_SUPPORTED environment variable set to \"YES\""
   exit 0
   fi
   cd "$SRCROOT/.."
   ./gradlew :core:embedAndSignAppleFrameworkForXcode

 - Remove "framework -core" in Other Linker Flags (navigation: Targets --> Build Settings --> Linking - General --> Other Linker Flags))



