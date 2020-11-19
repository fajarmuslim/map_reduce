# PR_MapReduce_13517149

Repo ini dibuat untuk memenuhi PR MapReduce mata kuliah Pengembangan Aplikasi Terdistribusi

Petujunjuk menjalankan program 
1. Install hadoop standalone mengikuti tutorial berikut: 
<br/>
https://www.digitalocean.com/community/tutorials/how-to-install-hadoop-in-stand-alone-mode-on-ubuntu-18-04

2. Pastikan env variable sudah di set dengan baik. Berikut setting pada laptop saya. 
* export JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-amd64
* export PATH=${JAVA_HOME}/bin:${PATH}
* export HADOOP_CLASSPATH=${JAVA_HOME}/lib/tools.jar
<br/>
bisa dijalankan sebelum compile program di terminal agar env variable dapat dikenali

3. Compile java program menjadi file .class, Misal untuk WordCount</br>
/usr/local/hadoop/bin/hadoop com.sun.tools.javac.Main WordCount.java

4. Buat jar file dari file .class tersebut. Misal untuk WordCount sebagai berikut <br/>
jar cf WordCount.jar WordCount*.class

5. Buat direktori input dan siapkan file txt didalamnya yang berisi text beberapa baris

6. Jalankan jar dengan command sebagai berikut.Misal untuk WordCount sebagai berikut </br>
/usr/local/hadoop/bin/hadoop jar WordCount.jar WordCount input output
<br/> Sebagai catatan : direktori output akan dibuat secara otomatis saat menjalankan program

7. Hasil akan tersimpan pada direktori output

## Hasil Eksekusi Program
Semua hasil eksekusi disimpan dalam folder screenshot pada repo ini

