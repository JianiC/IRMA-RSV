#!/bin/bash

##change directory

cwd=$(pwd)

##check if RSV1 and RSV2 is here
var=$(find . -d -name '*_RSV1')
#echo "$var"	

###create a sum txt file 
echo 'id	genotype	length' > sum.txt


for i in $var
do
	#echo "$i" ## RSV1 output dir
	
	n=$(sed "s/_RSV1//g" <<< $i)
	n="${n:2}"
	#echo "$n" ## get id 

	m=$(sed "s/RSV1/RSV2/g" <<< $i)

	if [ -d "$m" ]
	then
		#echo "$m is found" ## check if RSV2 exists
			
		if [ -d "$i/amended_consensus" ] && [ -d "$m/amended_consensus" ]
		then

			## generate fasta file with reference and do alignment
			## find the genotype determined by SH-G-F region
			rf=$(find $m -name '*-SHGF.fasta') 
			gp=$(awk -F "/" '{print $3}' <<< $rf | awk -F "-" '{print $1}')
			#echo "genotype is $gp"
			
			## generate alignment file 
			cat $m/amended_consensus/*.fa ./reference/RSV2/$gp*.fa ./reference/RSV0/$gp.fa  > $m/"$n"_RSV2_rf.fasta
			mafft $m/"$n"_RSV2_rf.fasta > $m/"$n"_RSV2_rf_al.fasta
	
			cat $i/amended_consensus/*.fa ./reference/RSV1/$gp*.fa ./reference/RSV0/$gp.fa> $i/"$n"_RSV1_rf.fasta			
			mafft $i/"$n"_RSV1_rf.fasta > $i/"$n"_RSV1_rf_al.fasta		

            	## process alignment files with java method
			
			java nest $i/"$n"_RSV1_rf_al.fasta $m/"$n"_RSV2_rf_al.fasta >> sum.txt
			
			echo "$n WGS fasta file has been created"
				
		

		else
			echo "Error: amended_consensus of $n does not exists, assemnbly might be failed."
		fi
		
	else
		echo "Error: Directory $m does not exists."
	fi	
	
done
		

