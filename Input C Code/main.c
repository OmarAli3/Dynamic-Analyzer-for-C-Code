#include <stdio.h>
int fun(){
return 1;
}

struct tag_name {
   int member1;
   int member2;
};

int main(){
int x = 0,y=0,z=0,u=0,o=0,xx=0;
if(x == 0){
x=2;
}
else
x=8;


while(++xx<5){
if (x == 0) {
    x = 2;
    y = 5;
    z = 7;
}else{
    x = 0;
}
switch (x){
    case 1:
        y = 2;
    case 2:
        x = 2;
        fun(x);
        break;
    default:
        z = 2;
}

if (x==4){goto to;}

int bob=0;
while(++bob<2) {y=8;break;x=2;}
while(++bob<5) y = 3;
for(int i = 0; i < 4; ++i)y = 3;
if(x == 2) y = 0;
if(x == 0) x = 0;
else{y=6;}

}

for(int i = 0; i < 4; ++i)
{
y = 3;
continue;
u = 4;
}

to:
x=o;
x=x==2?3:4;
return 0;
}