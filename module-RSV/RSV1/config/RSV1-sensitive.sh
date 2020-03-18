# HEADER
PARAM_FILE_NAME="RSV-sensitive"
PARAM_FILE_AUTHOR="S. Shepard"
PARAM_FILE_VERSION="1.0"
PARAM_FILE_DATE="2017-01-30"

SKIP_E=0				# skip reference elongation
GRID_ON=1				# use the grid
MAX_ROUNDS=10				# extended iterations

#this part has been added by Xueting
# CONSENSUS REFINEMENT & READ SELECTION 
QUAL_THRESHOLD=19			# average or median threshold for QUALITY reads
MIN_LEN=50		# minimum read length for QUALITY reads


# STAGES
MATCH_PROG="BLAT"			
SORT_PROG="LABEL"
ALIGN_PROG="SAM"
ASSEM_PROG="SSW"
