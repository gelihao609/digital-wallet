# Memo 
1. Original folder structure is maintained. run_tests.sh is not used.
2. The script to run is modified to compile and run java code. It takes arugments in the same order as example run.sh (which uses python).
3. This program can only handle the transaction amount of 150000~ish for it to build 3rd-degree friend network. So a counter is added in line 22 at src/Utility.java

# Usage
To run, git clone this repo to your local, cd digital-wallet and run in cmd $sh run.sh 

Note that the Java VM version is: 
Java version "1.8.0_101"
Java(TM) SE Runtime Environment (build 1.8.0_101-b13)
Java HotSpot(TM) 64-Bit Server VM (build 25.101-b13, mixed mode)


Please let me know if you have any questions alexatflorida@gmail.com