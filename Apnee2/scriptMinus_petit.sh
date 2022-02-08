#!/bin/sh


for achat in tests/LesAchats/*
do
	for achat2 in tests/LesAchats/*
	do
		
		java EssaiJoin $achat $achat2
	done
done

