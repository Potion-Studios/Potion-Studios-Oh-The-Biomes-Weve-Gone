architectury {
    common("forge", "fabric", "neoforge")
    platformSetupLoomIde()
}

val minecraftVersion = project.properties["minecraft_version"] as String

loom.accessWidenerPath.set(file("src/main/resources/biomeswevegone.accesswidener"))

sourceSets.main.get().resources.srcDir("src/main/generated/resources")

dependencies {
    modImplementation("net.fabricmc:fabric-loader:${project.properties["fabric_loader_version"]}")

    modCompileOnly("com.github.glitchfiend:TerraBlender-common:$minecraftVersion-${project.properties["terrablender_version"]}")
    modCompileOnly("corgitaco.corgilib:Corgilib-Fabric:1.21.1-${project.properties["corgilib_version"]}")
    modCompileOnly("dev.corgitaco:Oh-The-Trees-Youll-Grow-common:$minecraftVersion-${project.properties["ohthetreesyoullgrow_version"]}")
    modCompileOnly("software.bernie.geckolib:geckolib-common-$minecraftVersion:${project.properties["geckolib_version"]}")

    modCompileOnly("mcp.mobius.waila:wthit-api:fabric-${project.properties["WTHIT"]}")
}
