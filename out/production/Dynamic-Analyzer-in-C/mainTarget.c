#include <stdio.h>
int flag[25];
int fun ( ) {flag[0] = 1;
return 1 ;
flag[1] = 1;
}
struct tag_name {
int member1 ;
int member2 ;
}
;
int main ( ) {flag[2] = 1;
int x = 0 , y = 0 , z = 0 , u = 0 , o = 0 , xx = 0 ;
if ( x == 0 ) {flag[3] = 1;
x = 2 ;
}
else flag[4] = 1,x = 8 ;
while ( ++ xx < 5 ) {flag[5] = 1;
if ( x == 0 ) {flag[6] = 1;
x = 2 ;
y = 5 ;
z = 7 ;
}
else {flag[7] = 1;
x = 0 ;
}
switch ( x ) {
case 1 : flag[8] = 1;
y = 2 ;
case 2 : flag[9] = 1;
x = 2 ;
break ;
flag[10] = 1;
default : flag[11] = 1;
z = 2 ;
}
if ( x == 4 ) {flag[12] = 1;
goto to ;
flag[13] = 1;
}
int bob = 0 ;
while ( ++ bob < 2 ) {flag[14] = 1;
y = 8 ;
break ;
flag[15] = 1;
x = 2 ;
}
while ( ++ bob < 5 ) flag[16] = 1,y = 3 ;
for ( int i = 0 ;i < 4 ;++ i ) flag[17] = 1,y = 3 ;
if ( x == 2 ) flag[18] = 1,y = 0 ;
if ( x == 0 ) flag[19] = 1,x = 0 ;
else {flag[20] = 1;
y = 6 ;
}
}
for ( int i = 0 ;i < 4 ;++ i ) {flag[21] = 1;
y = 3 ;
continue ;
flag[22] = 1;
u = 4 ;
}
to : flag[23] = 1;
x = o ;
x = x == 2 ? 3 : 4 ;
for(int i=0;i<25;i++)if(flag[i])printf("%d\n",i);
return 0 ;
flag[24] = 1;
}
