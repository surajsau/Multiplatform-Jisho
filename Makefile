PRODUCT_NAME := kmm-template

PACKAGE := in.surajsau.jisho

# for unit test
PLATFORM_IOS := iOS Simulator,name=iPhone 14 Pro
FLAVOR ?= debug

.PHONY: setup
setup: # setup project dependencies if any
	@make setup-node
	@make generate-templates

.PHONY: clean
clean: # clean build
	rm -rf iosApp/build
	./gradlew clean

.PHONY: build-%
build-%: # generate {module}.xcframework to be shared in iosApp
	./gradlew $(*):assembleXCFramework

.PHONY: ios-build-%
ios-build-%: # generate {module}.xcframework to be shared in iosApp and move it to iosApp/build folder
	@make build-$(*)
	mkdir -p "iosApp/build"
	cp -r "$(*)/build/XCFrameworks/$(FLAVOR)/$(*).xcframework" "iosApp/build"

.PHONY: test-%
test-%: # run unit tests for core module
	./gradlew :core:$(*):test
	./gradlew :core:$(*):iosX64Test

.PHONY: create-module
create-module: # create new module
	node -e 'require("./tools/generate_module").generateModule()'

setup-node: # setup nodejs
	brew list node || brew install node
	npm install

generate-templates: # generate /tools/templates/generated with given PACKAGE
	node -e 'require("./tools/generate_templates").generateTemplateFolder("$(PACKAGE)")'