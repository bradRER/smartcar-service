create a new package named after your service
unit test the controller and service

*location and vehicleInfoAddl can be used as reference

your tests should be fast, meaning they do not require
you to spin up the entire project to run the test

inject a mocked version of any dependencies your
controller or service requires 

tell your mocked service what to return to the controller
depending on your test case
        doReturn(vehicleInfoAddl).when(vehicleInfoAddlService).create("fasdg");

each function should have at least 1 test in order
to cover all expected behavior

make sure your microservice uses @RequiredArgsConstructor
instead of @Autowired for dependencies