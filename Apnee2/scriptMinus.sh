#!/bin/sh



for achat in tests/LesAchats/*
do
	for achat2 in tests/LesAchats/*
	do
		
		java EssaiJoin $achat $achat2
	done
done

for vin1 in tests/LesVins/*
do
	for vin in tests/LesVins/*
	do
		
		java EssaiJoin $vin1 $vin
	done
done
