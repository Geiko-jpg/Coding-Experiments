from bs4 import BeautifulSoup
from pip._vendor import requests

def getPrimeData():
    # SETTING UP THE VARIABLES
    firstURL = 'https://www.worldometers.info/coronavirus/'
    page_firstInstance = requests.get(firstURL)

    soup = BeautifulSoup(page_firstInstance.content, 'html.parser')
    results = soup.find(class_='content-inner')

    countFindings = results.find_all('div', class_='maincounter-number')

    # FILTERING THE DATA
    ctr = 0
    for totes in countFindings:
        if ctr == 0:
            totalCases = totes.find('span')
        elif ctr == 1:
            deathToll = totes.find('span')
        elif ctr == 2:
            recoveryCases = totes.find('span')
        else:
            break
        ctr += 1

    # PRINTING THE CONTENTS
    print("CORONAVIRUS STATISTICS 2020\n")
    print("TOTAL COUNT (WorldWide):", totalCases.text)
    print("TOTAL DEATHS (WorldWide):", deathToll.text)
    print("TOTAL RECOVERIES (WorldWide):", recoveryCases.text , "\n")

def activeCases():
    # SETUP SOUP
    secondURL = 'https://www.worldometers.info/coronavirus/'
    page_secondInstance = requests.get(secondURL)

    secondSoup = BeautifulSoup(page_secondInstance.content, 'html.parser')
    primeDivResults = secondSoup.find('div', {"class" : 'panel_front'})

    # GET CURRENT INFECTED
    curInfected = primeDivResults.find('div', {"class" : 'number-table-main'})    
    print("CURRENTLY INFECTED PATIENT (WorldWide):", curInfected.text)

    # GET SPECIFIC CONDITIONS && PERCENTAGES
    specSearch = primeDivResults.find_all('span')
    ctr = 0
    for data in specSearch:
        if ctr == 0:
            mildCond = data.get_text().strip()
            
        elif ctr == 1:
            criticalCond = data.get_text().strip()
        ctr += 1

    findStrong = primeDivResults.find_all('strong')
    secCtr = 0
    for percData in findStrong:
        if secCtr == 0:
            mildPercentage = percData.get_text()
        elif secCtr == 1:
            critPercentage = percData.get_text()
        secCtr += 1
    
    print("MILD CONDITIONS (WorldWide):", mildCond, "( " + mildPercentage + "% )")
    print("CRITICAL CONDITIONS (WorldWide):", criticalCond, "( " + critPercentage + "% )", '\n')

if __name__ == "__main__":
    print("HELLO TEST FOR GITHUB")
    getPrimeData()
    activeCases()