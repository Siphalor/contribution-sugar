# Contribution Sugar

There's a lot of sugar out there:

Crystal sugar, cane sugar, rock sugar, beet sugar, *Sugar, Sugar* and syntactic sugar.

What we're really in need of, is a way to pay back the work of the contributors to our projects.

Introducing: **Contribution Sugar**

![logo](icon_large.png?raw=true)

## The Gist
This Minecraft mod will grant mod specific capes for all contributors to that mod.

It tries to be as small as possible so that it can easily snuggle in to other mods without users noticing it :)

## How it works
**Contribution Sugar** makes use of the information provided in `fabric.mod.json`. Contributors in this sense are people that are either declared `authors` or `contributors` in that file.

It will then look for the custom value `contributionsugar` where the texture locations for the cape and elytra texture are stored.

If more than one mod specifies a cape for a contributor then it will change each day to one of them.

### An example
```json5
{
    "schemaVersion": 1,
    "id": "example",
    "name": "Example Mod",
    "authors": [
        "MegaCoder1000"
    ],
    "contributors": [
        "HappySteve24"
    ],
    "custom": {
        "contributionsugar": {
            "cape": "example:cape.png",
            "elytra": "example:cape.png" // optional but usually in the same file
        }
    }
}
```

### I have a different Minecraft username!
First, I'm sorry for you.

Second, there's a workaround. Normally you only specify strings in the `authors` and `contributors` array, but it allows for more data to be attached. See here:

```json5
{
    "contributors": [
        {
            "name": "HappySteve24",
            "contact": {
                "minecraft": "SadSteve24"
            }
        }
    ]
    // ...
}
```

## I want to help with the development of this mod
This mod tries to be as small as possible (considering the jar file size). Therefore, the only contributions I'm gonna allow for this mod are those that reduce the file size.
