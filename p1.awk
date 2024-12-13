BEGIN { c=0;
}
{
If ($1=="d")
{
 c++;
 printf("%s\t%s\n",$5,$11);
}
}

END {
printf("the number of pkts drepped=%d\n",c);
}
