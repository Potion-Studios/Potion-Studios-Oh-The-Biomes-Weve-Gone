# 3.0.0
- Update to 1.21.4

# 2.3.8
- Fix Flower Dye Recipes
- Add BlueBerries to Blue Dye Recipe
- Improve a bunch of flowers hitboxes
- Update French (fr_fr) Translations (Credits: Brandcraf06)
- Add Missing Music Disc Description key to Language Files
- Improve Oddion and Pumpkin Warden dimension update logic

# 2.3.7
- Add Right-Clicking on Baby Villager With Weakness Potion with a Carved Pumpkin to turn them into a Pumpkin Warden
- Remove BWGItemTags for Dye Conversions, Replace with Explicit Recipes (Like Vanilla)
- Fix Tall Flowers only making 1 dye instead of 2

# 2.3.6
- Fix Vine like blocks not being compostable on Forge
- Sync biomeswevegone:temperate tag to c:is_temperate/overworld
- Match all BWG Biomes to Climate Tags Based on Temperature
- Full Serene Seasons Compatability
- Fix ManOWar Air Supply being different when spawned in on land
  - Normal Conditions ManOWar will die on land after 5 minutes
  - If the biome is marked as a dry biome the ManOWar will die sooner (random between 1 and 5 minutes)

# 2.3.5
- Make Soul Fruit Blindness Effect configurable
- Add Misc and Mob Spawn Config Reload Commands
- Update Many TreeGrowers to better match the tree they are growing
  - This includes making trees with large bases require 2x2 saplings  

# 2.3.4
- Give all BWG Entities Eye Heights
- Increase Oddion Hitbox Size
- Decrease Pumpkin Warden Hitbox Size
- Move Oddion to Entity Type Creature
  - This Change should fix large amounts of Oddions spawning in the same areas 
  - If you are experiencing issues with lots of Oddions we suggest killing off the ones in the area and letting the new ones spawn in with the new changes
- Move ManOWar to Entity Type Water Creature
- Update Hitbox Size when pose changes for Oddion and Pumpkin warden
- Decrease ManOWar Spawn Weight and Minimum Group Size

# 2.3.3
- Add ModMenu Support (Fabric) 
- Fix Oddion Despawning 
- Give ManOWar Custom Persistence
- Make Poison Ivy and Skyris Vine copy Vanilla Vine Properties
- Remove Replacement Recipe for Vanilla Crafting Table
- Fix Oddion Crop interactions being incorrect
- Add WTHIT Support for BWG Saplings, StemBlocks, and Oddion Crop
- Fix Chest Boat Recipes being incorrect 
- Improve ManOWar Color Selection Logic
- Fix pumpkin warden farming goals

# 2.3.2
- Remove Milk from Allium Oddion Soup Recipe, Change Nutrition value from 9 to 8
- Add BWG Sand Blocks and Mushrooms to Enderman Holdable BlockTag
- Add BlueBerry Bush to Fall Damage Resetting BlockTag
- Add BWG SandStone, Red Rock, Dacite, and Packed Ice Variants to Overworld Carver Replaceables
- Add Soul Fruit to Piglin Repellents ItemTag
- Add Palo Verde Logs Tag to Logs that Burn Tag
- Add Red Rock Bricks Block and Item Tags
- Add Glow Cane Block Tag
- Add Glow Bottle Block and Item Tags
- Fix Incorrect Chiseled Red Rock Bricks Recipe
- Fix Red Wool from Rose Petal Block Recipe having path typo

# 2.3.1
- Add Missing Spanish (Chile) Language Keys
- Add Missing Spirit Roots LootTable/Drops
- BoneMealing The Hydrange Hedge block will now Drop Hydrangea Hedges
- Fix BWG Planks not being shown to craft specific wood set crafting tables
- BoneMealing Grass Block/Lush Grass Block/Overgrown Stone/Dacite will now grow not just grass but any flower from that biomes flower pool
- Make Tall Allium and Allium Flower Bush make Magenta Dye to match Vanilla
- Add Built-in Support for Repurposed Structures (Adds Forager Houses to Villages) (NeoForge & Fabric) (Credits: TelepathicGrunt)
- Add Pale Pumpkin to Pale Pumpkin Seeds Recipe

# 2.3.0 -> Major Internal Changes and Refactors
- Add Spanish (Chile) Translations (es_cl) (Credits: Ganbare-Lucifer)
- Generalize Particle, Block and Item Color Registrations
- Finish and Tweak German Translations (de_de) (Credits: ieguana)
- Fix StemBlock/AttachedStemBlock/PitcherCropBlock not being placeable on BWG Farmland (Fabric Only)
- Refactor Entity Renderers into client package and renderer/entity subpackage
- Refactor BWGSounds class from client package into sounds subpackage
- Remove unneeded super calls in block based Mixins
- Fix Cattail Sprout not being consumed when placed on a Campfire
- Decrease Cypress, and Palo Verde Tree File Sizes
- Refactor Platform FarmLandBlock to have Platform Name
- Move Config Classes to Config Package
- Correct BWG Pitcher Plant Item Models
- Slight Change to FruitBlockProcessor#finalizeProcessing to be more Efficient
- Add Config to Enable/Disable Spawning of Oddion and Man O War
- Add Storage Block Tags (Allium and Rose Petal Blocks) (#c name space)
- Fix BWG trades on NeoForge
- Add BWG Items to Wandering Trader Trades
- New BWG Trades Config with Wandering Trader Options, and Option to disable Forager Trades

# 2.2.5
- Make Pale Pumpkin Set Compostable
- Make GlowCane Shoots Compostable
- Make Cattail Sprouts Compostable
- Fix Animals not spawning in BWG Biomes due to missing block tags
- Add Goats to Howling Peaks
- Add Armadillos to Araucaria, Baobab Savannas, Ironwood Gour and Red Rock Valley
- Add Russian Translations (ru_ru) (Credits: j-tap)

# 2.2.4
- Fix White Mangrove Trees Canopies Floating above trunks
- Decrease White Mangrove Tree File Sizes and Rename
- Update Simplified Chinese (zh_cn) Translations (Credits: Crown-Fairy)
- Bonemealing Grass Blocks in Allium Shrubland gives chance of any color Alliums and Allium Bushes to grow
- Add Japanese Translations (ja_jp) (Credits: Abbage230)
- Decrease Giant Allium File Sizes
- Fix Howling Peaks spawning in Desert Peak Region
- Add Snow Drops to Eroded Borealis, and Howling Peaks
- Add Winter Cyclamen to Howling Peaks
- Add Protea to Fragment Jungle
- Hitting Fruit Blocks with a Projectile will now cause the block to break with the fruit dropping if it is fully grown
- Fix Incompatibily with Organics Mod
- Clicking on Fruit Leaves with Fruit Item in creative will now place the fruit block below the leaves (Assuming there is air below)
- Clicking on Fruit with Fruit Item in creative will now Age the Fruit Block

# 2.2.3
- Add Apple Fruit Decorator to Orchard Trees
- Make AttachedToLeavesDecorators private
- Fix Fruit Blocks not Generating on Fruit Leaves
- Update Ukrainian Language File
- Remove Bonemeal interactions between Fruit Leaves and Fruit Block
- Make Fruit Leaves only valid bonemeal targets when there is air below the block
- Make it so Flowering Leaves can't be bonemealed if placed by player
- Give BWGFruitBlock a Codec

# 2.2.2
- Remove unneeded BWGPumpkin Class
- Use Simpler Code for ManOWar Texture Location
- Fix Soul Torch Recipe with Soul Fruit overwriting Vanilla Recipe
- Decrease Yucca and Baobab Tree NBTTemplates File Size
- Rename Baobab tree files to match the tree name

# 2.2.1
- Match Pale jack o’lantern Light Level to Soul Fruit block light level
- Fix Fruit Block/Leaves Crash
- Decrease Bush nbt template file size
- Remove some Duplicate Language Keys from German Language File
- Prevent Enderman from Angering when player is wearing Pale Carved Pumpkin
- Allow Vanishing Enchantment on Pale Carved Pumpkin

# 2.2.0 SpookTober Update -> https://github.com/Potion-Studios/Oh-The-Biomes-Weve-Gone/discussions/149
New Features:
- Add Spirit Wood Set
- Add Pale Mud, Packed, and Bricks
- Add Fluorescent Cattails
- Add GlowCane and Glowcane powder
- Add Soul Fruit, Glow Bottles, Pale Pumpkins
- Add Pale Pumpkin Warden Variant
- Add Pale Bog Biome
- Add Bog Trial Structure

Fixes, Improvements and Other Changes:
- Optimize Crag Garden and Basalt Barrera Generation
- Move from using ID in Fruitblock for Leaves to using Supplier of the Leaves Block
- Increase Pumpkin Generation in Pumpkin Valley Biome
- Update German Language Translations
- Add Pixie Club Music Disc to Forgotten Village House and Temple Chest Pools

# 2.1.5
- Fix and Add Missing Mossy Red Rock and Mossy Stone Recipes
- Add Missing Foragers Table Recipe
- Use Common Dyes Tags for Colored Sand Recipes
- Add Stripped Woods and Logs to new Tags (Added in NeoForge 21.1.71 and Fabric API 0.106.0)
- Override getShadowRadius in Entity Renderers instead of using render method
- Make Entity Model Classes Package-Private, remove pointless override of getAnimation Method
- Move PoseStack Scaling into preRender Method
- Decrease Pumpkin Warden Shadow Radius
- Fix Incompatibility with William Wythers' Overhauled Overworld/Expanded Ecosphere
  - Remove SeaGrass Normal from Lush Stacks as SeaGrass Warm is the same feature but more common 

# 2.1.4 (Forge Only)
- Require Forge 52.0.20 as a minimum due to the addition of new Forge tags and implementation of Common Tags
- Remove unneeded Forge Specific Mixin json
- Remove NeoForge Tags and other Data from Forge Jar

# 2.1.3 (Fabric & Forge Only)
- Fix BWG Hanging Signs Crashing When placed

# 2.1.2
- Fix Peat Fuel having wrong burn value on NeoForge
- Decrease Mushroom Canopy and Trunk File Sizes
- Add Pies to new #c:pie tag
- Remove Extra Patch Grass Badlands from Bayou
- Fix being able to have more than 1 bwg boat in a stack
- Adjust Boat with Chest Lang to match Vanilla conventions
- Fix Overgrown Stone and Dacite not spreading

# 2.1.1-Beta (NeoForge Only)
- Move NeoForge Compostables and Fuels to Datamap
- Fixes Compostables not working on NeoForge

# 2.1.0-Beta
- Fix incompat with William Wythers' Expanded Ecosphere
- Fix Sakura Grove Order Cycle crash (Fixes Incompats with Cliff Tree)
- Fix BWG Ice missing from Mineable with Pickaxe Tag
- Add BWG Logs to Overworld Natural Logs Block Tag
- Fix Forge Block and Item Tags being in wrong directory
- Add Quicksand Damage type to NeoForge IS_ENVIRONMENT Damage Type Tag
- Fix JukeBox Song having wrong language key
- Add BWG Food to Common Food Item Tag
- Add Oddion Bulb to Crops tags
- Add Lush Farmland to NeoForge Villager Farmland Tags to allow villagers to plant on it

# 2.0.4-Beta
- Revert Fix Incompatibility with Slayers Beasts. -> Causes incompat with Terralith

# 2.0.3-Beta
- Fix BWG Chest Boats not Saving Data
- Fix Incompatibility with Slayers Beasts
- Return ResourceKey<CreativeModeTab> for our tabs
- Remove unused Tree NBTS, Refactor White Mangrove trees into folder

# 2.0.2-Beta
- Fix Colored Sand Recipes using colored sand instead of normal sand
- Update Skyris Village Forage, and Small Houses to non-legacy, add Custom Structure Processors
- Update Skyris Village Large Farm to non-legacy, decrease file size, improve crop randomization
- Update Skyris Village Animal Pen to non-legacy, add Animals

# 2.0.1-Beta
- Fix Missing Recipes for Doors, Trapdoors, Slabs, Stairs, Buttons, Pressure Plates, Signs and Fences
- Fix Oddion Foods Saturation Values

# 2.0.0-Beta
- Update to 1.21.1, Java 21
- Add NeoForge Support

# 1.2.2-Beta
- Use Fabric API for Point Of Interest Type Registration to fix Forager not getting job on Fabric
- Fix Sheep not being able to Eat off of Lush Grass
- Fix Cattail exploding on Campfire when campfire isn't lit
- Add Recipes for Colored Sand

# 1.2.1-Beta
- Fix Oddion Foods having wrong Nutrition Values
- Remove need for Explicit BlockPredicate Registration Method
- Update Custom Structure Processor Registration
- Require TYG 1.3.1 - Fixes Trees piercing surface
- Fix Some surface Rule issues in specific cases
- Require CorgiLib 4.0.3.2

# 1.2.0-Beta
- Update/Add To French Language File (FR_FR)
- Fix Dacite Variants not dropping cobbled dacite when minned and overgrown stone not dropping cobblestone
- Give Sakura Leaves Cherry sound on break
- Fix tall flowers not being able to be used to craft dyes
- Add Forager Trades and Config
- Add Vanilla Trades and Disable Option
- Fix WhitePuffBall age not being reset when picked, Don't call super.use on ages less than 1
- Fix WhitePuffBall growth being incorrect
- Call GameEvent#Shear on successful FireCracker Leaves and Hydrangea Hedge Shears
- Update WTHIT Plugin to new Format 
- Implement FruitBlockProcessor for Skyris Village TownCenters

# 1.1.2-Beta
- Set getBucketItemStack in ManOWar.java to correct Item
- Update Skyris Mason, Weaponsmith, Fletcher House and Armorer to non-legacy
- Use Processor on Skyris Mason, Skyris Fletcher House
- Remove Duplicate Skyris Butcher Shop Entry from Template Pool
- Update Chinese Language File (ZH_CN)
- Add Block and Item Tags for Alliums, Roses, Tulips, Amaranth, Sages and Daffodils
- Remove Winter Succulent and HorseWeed from small flowers tag
- Add Flowering Leaves and Flowering Bushes to Minecraft FlowerTag

# 1.1.1-Beta
- Remove a bunch of unneeded stuff
- Remove End Sand
- Fix Flower Patch Item being dull
- Add Wooden Fence Gates to Forge Wooden Fence Gates Tag
- Add Icy BiomeTag
- Remove Crimson Tundra, Frosted Taiga, and Frosted Coniferous Forest from Snowy BiomeTag
- Add Other Types of Flowers to Vanilla Flower Tag
- Fix Tillables not working on Forge
- Use Sided Success on Bush like blocks at end of use method
- Call GameEvent#BLOCK_CHANGE on Bush like blocks when they are updated
- Fix swamp biome disabling/enabling
- Update Required CorgiLib Version to 4.0.3.0
- Fix Snowy plants generating on top of non-full snow blocks.

# 1.1.0-Beta
- Fix some leaves having incorrect drops and not dropping leaves when sheared
- Make Tall Prairie Grass only placeable on BlockTag Dirt to be the same as normal prairie grass
- Make All VineBlocks Compostable and Climbable
- Move All leaves into Wood Class/Creative Tab
- Fix Holly Berry Leaves Drops
- Fix Grass Generating in caves in Allium Shrubland
- Adjust Just Like Grandma's Lang to say both pies
- Make Stairs and Slabs Tags for BWG SandStone Sets and add to Respected Fabric Tags
- Make BWG Desert Plants, Leaf Piles, Flower and Clover patches Compostable
- Add Imbued wood to Fabric Budding Blocks Tags
- Add all BWG Biomes to Minecraft IS_OVERWORLD tag and fabric IN_OVERWORLD tag
- Fix the disabling of some biomes not working
- Fix Cattail Drops
- Change the way we do Biome Modifiers to use biomes and not tags to prevent accidentally adding our features to non-vanilla biomes
- Fix Incompatibilities with Excessive Building, Blooming Biosphere, and Slayer's Beasts
- Update Oh The Trees You'll Grow to 1.3.0 (Required)
- Fix Winter Flowers not being compostable
- Add BWG Flower Default/Warm Feature Equivalents to Vanilla Biomes
- Make Vanilla Additions configurable on Fabric (Not available on forge due to it being in json and not in code)
- Give Horseweed correct box shape
- Add Oddion Bulb to Villager Plantable Seeds ItemTag
- Add Mushrooms to Forge Mushroom ItemTag
- Add a bunch of BiomeTags

# 1.0.6-Beta
- Fix Skyris Hanging Sign Textures being swapped
- Fix Blue Enchanted Sapling Crashing on place
- Changes to Fruit Leaves
  - Pass Supplier of BWGFruitBlock to BWGFruitLeavesBlock instead of supplier of blockstate
  - Prevent Bonemealing when leaves are placed by players/decayable/Persistence Value
    - Reason for this is fruit should only really be growing when the leaves are attached to a tree
- Fix Fruit Leaves Crash
- Fix Yucca Trees not being growable on sand tagged blocks
- Add Cracked Sand and Sandy Dirt to Sand Block and item tags
- Make Cypress Saplings use 2 by 2
- Fix Yucca Leaves Models
- Add Light to Fairy Slipper
- Make all BWG flowers have No Occlusion 

# 1.0.5-Beta
- Workaround for a crash involving fruit leaves decay (No Drops)
- Change in how decaying leaves drop there fruit
- Change PrairieGrassMixin.java to use Inject to be more compatible with other mods

# 1.0.4-Beta
- Use Chunk Seeded Random for Crag Gardens Extensions
- Fix Palo Verde Logs and Wood not having drops
- Fix Orchard Trees requiring 2x2
- Fix Incompatibility with Nature's Spirit

# 1.0.3-Beta
- Fix Lush Farmland and grass not having loottables
- Fix sandy farmland not having a loottable
- Remove Vanilla Igloos from Shattered Glacier
- Fix a bunch of Leaves drops
- Remove Extra ModPlatform Classes, Add getConfigPath to PlatformHandler
- Custom Mushroom Sizes
- Add Huge Mushroom growers to mushrooms blocks
- Add Hanging Sign Recipes
- Make advancements not all Challenges

# 1.0.2-Beta
- Fix Golden Apple from Green Apple Recipe overriding Vanilla Recipe
- Fix Jacaranda Bushes Missing Collision
- Move Platform based things to AutoService
- Fix Wood Leaves being in Axe Mineable instead of Hoe Mineable
- Fix Fruitblocks not dropping when the leaf above them decays
- Fix palm saplings requiring 2x2 and dirt and not sand
- Fix Firecracker Leaves dropping fir saplings
- Remove the need for PrairieGrassMixin.java on Forge Side by using BoneMealEvent

# 1.0.1-Beta
- Allow BoneMealing Allium Flower Bushes into Tall Alliums
- Fix Crashing when going near fruit blocks on Fabric
- Fix Podzol Floors in Cakes under Sakura Grove
- Fix incompatibility with William Wythers' Overhauled Overworld
- Add BWG Logs to Logs That Burn Tag
- Fix a bunch of incorrect rendertypes around Plant like blocks
- Remove Surface Lava Pools from Forest like biomes
- Fix ores not genning in Eroded Borealis
- BoneMealing Tall Alliums Makes Giant Alliums
- Increase rate of Pumpkin in Pumpkin Valley

# 1.0.0-Beta
- Initial release