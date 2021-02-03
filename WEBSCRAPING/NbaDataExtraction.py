from bs4 import BeautifulSoup
from pip._vendor import requests

def extractPerGameTable():
    # Setting up variables = Nested Dictionaries
    perGameHashMap = {} # per game hashmap datastructure
    # Setting up Website Connection
    websiteURL = 'https://www.basketball-reference.com/players/r/randlju01.html#all_per_game' # Target Website
    pageInstance = requests.get(websiteURL)
    
    soup = BeautifulSoup(pageInstance.content, 'html.parser')
    rawFindings = soup.find(id='div_per_game')
    
    # first row extraction 2014-15
    firstRow = rawFindings.find(id='per_game.2015') # find all per_game.2015
    perGameHashMap['season2014-15'] = {}
    concentrateFirstRow = firstRow.find_all('td') # find all table columns
    for firstRowData in concentrateFirstRow: # loop through all data columns
        getFirstRowAttribute = firstRowData.get('data-stat') # finds all 'data-stat' attribute and its value
        extractedData = firstRowData.get_text().strip() # extract all column data from first row
        perGameHashMap['season2014-15'][getFirstRowAttribute] = extractedData # input to nested Hashmap key: 'season 2014-15'

    print("SEASON 2014-2015 DATA\n")     
    print(perGameHashMap['season2014-15']) # check season2014-15
    print('\n') # divider

    #second row extraction 2015-16
    secondRow = rawFindings.find(id='per_game.2016')
    perGameHashMap['season2015-16'] = {}
    concentrateSecondRow = secondRow.find_all('td')
    for secondRowData in concentrateSecondRow:
        getSecondRowAttribute = secondRowData.get('data-stat') 
        extractedDataSecondRow = secondRowData.get_text().strip()
        perGameHashMap['season2015-16'][getSecondRowAttribute] = extractedDataSecondRow

    print("SEASON 2015-2016 DATA\n")
    print(perGameHashMap['season2015-16']) # checkseason2015-16
    print('\n')
    
    #third row extraction 2016-17
    thirdRow = rawFindings.find(id='per_game.2017')
    perGameHashMap['season2016-17'] = {}
    concentrateThirdRow = thirdRow.find_all('td')
    for thirdRowData in concentrateThirdRow:
        getThirdRowAttribute = thirdRowData.get('data-stat')
        extractedDataThirdRow = thirdRowData.get_text().strip()
        perGameHashMap['season2016-17'][getThirdRowAttribute] = extractedDataThirdRow

    print("SEASON 2016-2017 DATA\n")
    print(perGameHashMap['season2016-17']) # check season2016-17
    print('\n')

    # fourth row extraction 2017-18
    fourthRow = rawFindings.find(id='per_game.2018')
    perGameHashMap['season2017-18'] = {}
    concentrateFourthRow = fourthRow.find_all('td')
    for fourthRowData in concentrateFourthRow:
        getFourthRowAttribute = fourthRowData.get('data-stat')
        extractedDataFourthRow = fourthRowData.get_text().strip()
        perGameHashMap['season2017-18'][getFourthRowAttribute] = extractedDataFourthRow
    
    print("SEASON 2017-2018 DATA\n")
    print(perGameHashMap['season2017-18']) # check season2017-18
    print('\n')

    # fifth row extraction 2018-19
    fifthRow = rawFindings.find(id='per_game.2019')
    perGameHashMap['season2018-19'] = {}
    concentrateFifthRow = fifthRow.find_all('td')
    for fifthRowData in concentrateFifthRow:
        getFifthRowAttribute = fifthRowData.get('data-stat')
        extractedDataFifthRow = fifthRowData.get_text().strip()
        perGameHashMap['season2018-19'][getFifthRowAttribute] = extractedDataFifthRow

    print("SEASON 2018-2019 DATA\n")
    print(perGameHashMap['season2018-19']) # check season2018-19
    print('\n')

    # sixth row extraction 2019-20
    sixthRow = rawFindings.find(id='per_game.2020')
    perGameHashMap['season2019-20'] = {}
    concentrateSixthRow = sixthRow.find_all('td')
    for sixthRowData in concentrateSixthRow:
        getSixthRowAttribute = sixthRowData.get('data-stat')
        extractedDataSixthRow = sixthRowData.get_text().strip()
        perGameHashMap['season2019-20'][getSixthRowAttribute] = extractedDataSixthRow

    print("SEASON 2019-2020 DATA\n")
    print(perGameHashMap['season2019-20']) # check season2019-20
    print('\n')

    # seventh row extraction 2020-21
    seventhRow = rawFindings.find(id='per_game.2021')
    perGameHashMap['season2020-21'] = {}
    concentrateSeventhRow = seventhRow.find_all('td')
    for seventhRowData in concentrateSeventhRow:
        getSeventhRowAttribute = seventhRowData.get('data-stat')
        extractedDataSeventhRow = seventhRowData.get_text().strip()
        perGameHashMap['season2020-21'][getSeventhRowAttribute] = extractedDataSeventhRow
    
    print("SEASON 2020-2021 DATA\n")
    print(perGameHashMap['season2020-21']) # check season2020-21
    print('\n')

    # Extract Footer Section
    findFooter = rawFindings.find('tfoot') # extract footer section
    findRowsFooter = findFooter.find_all('tr')

    perGameHashMap['career'] = {}
    perGameHashMap['4season'] = {}
    perGameHashMap['2season'] = {}
    perGameHashMap['1season'] = {}
    i = 0 # initialize counter
    for countRowsFooter in findRowsFooter:
        if i == 0: # career row
            concentrateCareerRow = countRowsFooter.find_all('td')
            for careerData in concentrateCareerRow:
                getCareerAttribute = careerData.get('data-stat')
                extractCareerData = careerData.get_text().strip()
                perGameHashMap['career'][getCareerAttribute] = extractCareerData
        elif i == 2: # 4 seasons row
            concentrate4SeasonsRow = countRowsFooter.find_all('td')
            for fourSeasonsData in concentrate4SeasonsRow:
                get4SeasonsAttribute = fourSeasonsData.get('data-stat')
                extract4SeasonData = fourSeasonsData.get_text().strip()
                perGameHashMap['4season'][get4SeasonsAttribute] = extract4SeasonData
        elif i == 3: # 2 seasons row
            concentrate2SeasonsRow = countRowsFooter.find_all('td')
            for twoSeasonsData in concentrate2SeasonsRow:
                get2SeasonsAttribute = twoSeasonsData.get('data-stat')
                extract2SeasonData = twoSeasonsData.get_text().strip()
                perGameHashMap['2season'][get2SeasonsAttribute] = extract2SeasonData
        elif i == 4: # 1 seasons row
            concentrate1SeasonsRow = countRowsFooter.find_all('td')
            for oneSeasonsData in concentrate1SeasonsRow:
                get1SeasonsAttribute = oneSeasonsData.get('data-stat')
                extract1SeasonData = oneSeasonsData.get_text().strip()
                perGameHashMap['1season'][get1SeasonsAttribute] = extract1SeasonData
        i += 1

    print("CAREER DATA\n")
    print(perGameHashMap['career']) # check career row data
    print('\n')

    print("4 SEASON DATA\n")
    print(perGameHashMap['4season']) # check 4 season row data
    print('\n')

    print("2 SEASON DATA\n")
    print(perGameHashMap['2season']) # check 2 season row data
    print('\n')

    print("1 SEASON DATA\n")
    print(perGameHashMap['1season']) # check 1 season row data
    print('\n')

# main function
if __name__ == "__main__":
    extractPerGameTable()    
