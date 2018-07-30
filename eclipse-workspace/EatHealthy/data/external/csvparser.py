#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Fri Jul 27 11:07:43 2018

@author: xianqu
"""
import pandas as pd

nutrient_xls = pd.read_excel('1.21 Summary Tables - updated August 2017.xls', 'Nutrients', index_col=None)
nutrient_xls.to_csv('nutrient.csv', encoding='utf-8')

energyinfants_xls = pd.read_excel('1.21 Summary Tables - updated August 2017.xls', 'Energy Infants', index_col=None)
energyinfants_xls.to_csv('energyinfants.csv', encoding='utf-8')

energychildren_xls = pd.read_excel('1.21 Summary Tables - updated August 2017.xls', 'Energy Children', index_col=None)
energychildren_xls.to_csv('energychildren.csv', encoding='utf-8')

energyadults_xls = pd.read_excel('1.21 Summary Tables - updated August 2017.xls', 'Energy Adults', index_col=None)
energyadults_xls.to_csv('energyadults.csv', encoding='utf-8')
