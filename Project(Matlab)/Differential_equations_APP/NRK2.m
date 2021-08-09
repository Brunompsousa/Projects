% DADOS
% f - funcao string que define y'=f(t,y(t))
% [a,b] - intervalo
% N     - numero de passos

% Bruno Sousa - 2019103045
% Raquel Mendes - 2012016451

function y = NRK2(f,a,b,n,y0)

h = (b-a)/n;
t = a:h:b;
y = zeros(1,n+1);
y(1) = y0;

for i=1:n
    
    t(i+1) = t(i)+h;
    k1 = h*f(t(i),y(i));
    k2 = h* f(t(i)+h,y(i)+k1);
    
    y(i+1) = y(i) + ((k1+k2)/2);
    
end
end

