% Bruno Sousa - 2019103045
% Raquel Mendes - 2012016451

function y = NEulerM(f,a,b,n,y0)
h = (b-a)/n;
t = a:h:b;
y = zeros(1,n+1);
y(1) = y0;

for i=1:n
    y(i+1) = y(i)+h*f(t(i),y(i)); 
end
end