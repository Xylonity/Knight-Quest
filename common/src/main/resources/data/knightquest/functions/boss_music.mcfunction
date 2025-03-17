scoreboard objectives add nethermantheme dummy
scoreboard objectives add musicSource dummy

execute as @a at @s if entity @e[type=knightquest:netherman,distance=..130] run tag @s add nethermanthemeplays
execute as @a[tag=nethermanthemeplays] if score @s musicSource matches 0 run scoreboard players set @s musicSource 2

execute as @a[tag=nethermanthemeplays] if score @s musicSource matches 2 run scoreboard players add @s nethermantheme 1

execute as @a at @s unless entity @e[type=knightquest:netherman,distance=..130] run tag @s remove nethermanthemeplays
execute as @a at @s unless entity @e[type=knightquest:netherman,distance=..130] if score @s musicSource matches 2 run stopsound @s record knightquest:the_architect_of_chaos
execute as @a at @s unless entity @e[type=knightquest:netherman,distance=..130] if score @s musicSource matches 2 run scoreboard players set @s nethermantheme 0
execute as @a at @s unless entity @e[type=knightquest:netherman,distance=..130] if score @s musicSource matches 2 run scoreboard players set @s musicSource 0

execute as @a if score @s musicSource matches 2 if score @s nethermantheme matches 1 run stopsound @s music

execute as @a if score @s musicSource matches 2 if score @s nethermantheme matches 2 at @s run playsound knightquest:the_architect_of_chaos record @s ~ ~ ~ 0.7
execute as @a if score @s musicSource matches 2 if score @s nethermantheme matches 2 run scoreboard players set @s nethermantheme 3

execute as @a if score @s musicSource matches 2 if score @s nethermantheme matches 3620.. run scoreboard players set @s nethermantheme 2

execute as @a if score @s musicSource matches 1 run scoreboard players add @s nethermantheme 1
execute as @a if score @s musicSource matches 1 if score @s nethermantheme matches 3620.. run scoreboard players set @s musicSource 0
execute as @a if score @s musicSource matches 1 if score @s nethermantheme matches 3620.. run scoreboard players set @s nethermantheme 0

execute as @a unless score @s musicSource matches 0..2 run scoreboard players set @s musicSource 0