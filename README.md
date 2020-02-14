# IRMA-RSV

## Nesting RSV pseudo-segment Library output
Assembly RSV sequence read with pseudo-segments instead of WGS can fix some low QC reads
Two RSV module: RSV-SHGF library and RSV-without SHGF library are created due the interest of RSV cell surface genomes.

### To run the nesting scripts
```
$git clone https://github.com/JianiC/IRMA-RSV/tree/master/IRMA-segments_combine

$./comb.sh
```
### Requirements
Gene alignment tool MAFFT need to be installed under ./src

Required Java JDK8 or higher to be installed
In case of java version issue, you could also compile the java code by yourself
```
javac ./nest.java
```
RSV IRMA output directory need to be moved under the same directory
IRMA output from different library need to be labeled as "*_RSV_1" and ""*_RSV_2"

### How it works
Step1: Make sure IRMA output directories exist.

Step2: The ORF of different gene regions are defined with reference sequence considering the possible issue at the intergenic regions.

Step3: Different gene regions from IRMA consensus read are nested together.

Step4: Alignment with reference sequence file is also generated.

Step5: A sum file will be created contain the genotype and length information for all RSV samples under the directory.
