#PolyHacks 2017 App
Work in progress, may eat your cat

##TODO
- Make a map of the IST
- Decide on a license

##Build Instructions
(First time only)

`npm install`

`mkdir www` (don't ask why this is required, I have no idea)

`ionic add platform $platform_of_choice`

(after the first time)

`ionic build $platform_of_choice`

`ionic run $platform_of_choice`

(for quick debugging)

`ionic serve`

(this just runs the app on your computer and lets you play with it in the browser)

##Issues setting up the build environment
If you get some error about node-sass, try this:

`sudo ln -s /usr/bin/nodejs /usr/bin/node`

If it tells you that this is not a Cordova project, try this:

(from the project root) `mkdir www`
