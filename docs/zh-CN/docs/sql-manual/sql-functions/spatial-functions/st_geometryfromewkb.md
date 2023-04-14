---
{
    "title": "ST_GeometryFromEWKB,ST_GeomFromEWKB",
    "language": "zh-CN"
}
---

<!-- 
Licensed to the Apache Software Foundation (ASF) under one
or more contributor license agreements.  See the NOTICE file
distributed with this work for additional information
regarding copyright ownership.  The ASF licenses this file
to you under the Apache License, Version 2.0 (the
"License"); you may not use this file except in compliance
with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
KIND, either express or implied.  See the License for the
specific language governing permissions and limitations
under the License.
-->

## ST_GeometryFromEWKB,ST_GeomFromEWKB

### Syntax

`GEOMETRY ST_GeometryFromEWKB(VARCHAR EWKB)`

### Description

将一个扩展 WKB（Extended Well-known binary）转化为对应的内存的几何形式。
由于doris的地理函数基于 WGS84 坐标系实现的，因此只能解析SRID为4326的EWKB数据，对于SRID不为4326的EWKB数据返回 NULL。

### example

```
mysql> select ST_AsText(ST_GeometryFromEWKB(ST_AsEWKB(ST_Point(24.7, 56.7))));
+-----------------------------------------------------------------+
| st_astext(st_geometryfromewkb(st_asewkb(st_point(24.7, 56.7)))) |
+-----------------------------------------------------------------+
| POINT (24.7 56.7)                                               |
+-----------------------------------------------------------------+
1 row in set (0.04 sec)

mysql> select ST_AsText(ST_GeometryFromEWKB(ST_AsEWKB(ST_GeometryFromText("LINESTRING (1 1, 2 2)"))));
+-----------------------------------------------------------------------------------------+
| st_astext(st_geometryfromewkb(st_asewkb(st_geometryfromtext('LINESTRING (1 1, 2 2)')))) |
+-----------------------------------------------------------------------------------------+
| LINESTRING (1 1, 2 2)                                                                   |
+-----------------------------------------------------------------------------------------+
1 row in set (0.11 sec)

mysql> select ST_AsText(ST_GeometryFromEWKB(ST_AsEWKB(ST_Polygon("POLYGON ((114.104486 22.547119,114.093758 22.547753,114.096504 22.532057,114.104229 22.539826,114.106203 22.542680,114.104486 22.547119))"))));
+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| st_astext(st_geometryfromewkb(st_asewkb(st_polygon('POLYGON ((114.104486 22.547119,114.093758 22.547753,114.096504 22.532057,114.104229 22.539826,114.106203 22.542680,114.104486 22.547119))')))) |
+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| POLYGON ((114.104486 22.547119, 114.093758 22.547753, 114.096504 22.532057, 114.104229 22.539826, 114.106203 22.54268, 114.104486 22.547119))                                                      |
+----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
1 row in set (0.03 sec)

mysql> select ST_AsText(ST_GeomFromEWKB(ST_AsEWKB(ST_GeometryFromText("LINESTRING (1 1, 2 2)"))));
+-------------------------------------------------------------------------------------+
| st_astext(st_geomfromewkb(st_asewkb(st_geometryfromtext('LINESTRING (1 1, 2 2)')))) |
+-------------------------------------------------------------------------------------+
| LINESTRING (1 1, 2 2)                                                               |
+-------------------------------------------------------------------------------------+
1 row in set (0.02 sec)

mysql> select ST_AsText(ST_GeomFromEWKB(ST_AsEWKB(ST_Polygon("POLYGON ((114.104486 22.547119,114.093758 22.547753,114.096504 22.532057,114.104229 22.539826,114.106203 22.542680,114.104486 22.547119))"))));
+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| st_astext(st_geomfromewkb(st_asewkb(st_polygon('POLYGON ((114.104486 22.547119,114.093758 22.547753,114.096504 22.532057,114.104229 22.539826,114.106203 22.542680,114.104486 22.547119))')))) |
+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
| POLYGON ((114.104486 22.547119, 114.093758 22.547753, 114.096504 22.532057, 114.104229 22.539826, 114.106203 22.54268, 114.104486 22.547119))                                                  |
+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+
1 row in set (0.03 sec)

//解析WKB数据返回NULL
mysql> select ST_AsText(ST_GeometryFromEWKB(ST_AsBinary(ST_GeometryFromText("LINESTRING (1 1, 2 2)"))));
+-------------------------------------------------------------------------------------------+
| st_astext(st_geometryfromewkb(st_asbinary(st_geometryfromtext('LINESTRING (1 1, 2 2)')))) |
+-------------------------------------------------------------------------------------------+
| NULL                                                                                      |
+-------------------------------------------------------------------------------------------+
1 row in set (0.02 sec)

```
### keywords
ST_GEOMETRYFROMEWKB,ST_GEOMFROMEWKB,ST,GEOMETRYFROMEWKB,GEOMFROMEWKB,EWKB