% d = round(rand(1,10000000) * 10000000);ds = sort(d);
fid = fopen('exp.txt','w');
for ii = 1:length(ds)
    fprintf(fid,'%d',ds(ii));
    fprintf(fid,'\r\n');
end
fclose(fid);
