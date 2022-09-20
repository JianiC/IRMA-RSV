# IRMA-RSV
## Run IRMA RSV module
For pair read:
`IRMA <RSV-config> <R1.fastq.gz/R1.fastq> <R2.fastq.gz/R2.fastq> <sample_name>`
For single read:
`IRMA <RSV-config> <fastq/fastq.gz> <sample_name>`



## Run IRMA RSV with pseudo-segment (to fix the low QC, usually the homopolymer issue)
```
IRMA <RSV0-config> <fastq/fastq.gz> <sample_name_RSV1>
IRMA <RSV0-config> <fastq/fastq.gz> <sample_name_RSV2>
```
## Nesting RSV pseudo-segment Library output
Assembly RSV sequence read with pseudo-segments instead of WGS can fix some low QC reads
Two RSV module: RSV-SHGF library and RSV-without SHGF library are created due the interest of RSV cell surface genomes.

### To run the nesting scripts

RSV IRMA output directoryies, .sh, reference directory, java screipts need to be moved under a same directory
IRMA output from different libraries need to be labeled as _"*_RSV1"_ and _"*_RSV2"_

**On Local**

Gene alignment tool MAFFT need to be installed under ./src
```
## Compile the java
javac ./nest.java
sh comb.sh >> comb.log

```
**On Server**
```
## Add module load MAFFT in shell script

javac ./nest.java
sh comb.sh >> gacrc_comb.log
```

### How this pipeline works
Step1: .sh will check the exists of IRMA output _RSV1 and _RSV2 directories and amended_consensus sub directories.

Step2: The ORF of different gene regions are searched by allignment with reference sequence considering the possible issue at the intergenic regions.

Step3: Different gene regions from IRMA consensus read are nested together.

Step4: Alignment with reference sequence file is also generated.

Step5: sum.txt be created contain the genotype and length information for all RSV samples under the directory; Recommend to generate comb.log to double check if all samples have been processed correctly.
