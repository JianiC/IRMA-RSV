# HEADER
PARAM_FILE_NAME="RSV-pgm3"
PARAM_FILE_AUTHOR="Jiani.C"
PARAM_FILE_VERSION="1.0"
PARAM_FILE_DATE="2018-11-16"

GRID_ON=1

# PERFORMANCE
MATCH_PROC=16				# grid maximum processes for the MATCH
ALIGN_PROC=16				# grid maximum processes for the rough align
ASSEM_PROC=16			# grid maximum processes for assembly

# CONSENSUS REFINEMENT & READ SELECTION
QUAL_THRESHOLD=19			# average or median threshold for QUALITY reads
MIN_LEN=50				# minimum read length for QUALITY reads


MIN_FI=0.02				# minimum insertion variant frequency
MIN_FD=0.03				# minimum deletion variant frequency

ALIGN_PROG="BLAT"
