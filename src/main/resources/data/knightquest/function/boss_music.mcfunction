scoreboard objectives add nethermantheme dummy

execute as @a at @s if entity @e[type=knightquest:netherman,distance=..130] run tag @s add nethermanthemeplays
scoreboard players add @a[tag=nethermanthemeplays] nethermantheme 1

execute as @a at @s unless entity @e[type=knightquest:netherman,distance=..130] run stopsound @s record knightquest:netherman_boss_music
execute as @a at @s unless entity @e[type=knightquest:netherman,distance=..130] run scoreboard players set @s nethermantheme 0
execute as @a at @s unless entity @e[type=knightquest:netherman,distance=..130] run tag @s remove nethermanthemeplays

execute as @a at @s if score @s nethermantheme matches 1.. run stopsound @s music
execute as @a at @s if score @s nethermantheme matches 2500.. run scoreboard players set @s nethermantheme 2
execute as @a at @s if score @s nethermantheme matches 2 run playsound knightquest:netherman_boss_music record @s ~ ~ ~ 0.7
