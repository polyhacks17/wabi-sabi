#PolyHacks 2017 App
:construction::rotating_light: Work in progress, may eat your cat :rotating_light::construction:

##TODO
- Remove all placeholders, replace with actual stuff

##Build Instructions :wrench:
(First time only)

`npm install -g cordova ionic` (you may have to run this command as sudo)

`npm install`

`ionic add platform $platform_of_choice`

(after the first time)

`ionic build $platform_of_choice`

`ionic run $platform_of_choice`

(for quick debugging)

`ionic serve`

(this just runs the app on your computer and lets you play with it in the browser)

##Issues setting up the build environment?
If you get some error about node-sass, try this:

`sudo ln -s /usr/bin/nodejs /usr/bin/node`