# RickAndMorty

<img width="800" alt="rick_and_morty" src="https://github.com/canozgan/RickAndMorty/assets/129083272/a7cab281-5aa7-4d5a-a8a3-d0baf3c2a03a">

General Specifications

1)It is an application where the user can list Rick and Morty series locations, the characters in the location and view the details of the characters.

2)There is a horizontal list and a vertical list on the main page of the application. Rick and Morty series locations are listed in the horizontal list, and the characters in the selected location are listed in the vertical list.

3)The user can view the character's details (type, gender, etc.) by clicking on the character in the vertical list.

4)The user can also use the application in landscape mode if he wishes.

Technical Specifications

1)Rick and Morty series locations, characters in location and details of characters are taken from a remote server in Json file format.

2)Retrofit and Gson library were used to pull data from the server and transfer this data to objects. This process is done asynchronously without locking the main thread that the user interacts with.

3)Recycler view technology was used during the design of the horizontal and vertical list.

4)The horizontal list initially has 20 locations. When the user comes to the end by scroll right, loading item symbol is added to the end of the list temporarily and new data is requested from the server. When new data arrives, the loading item symbol is removed and new data is added to the list.
