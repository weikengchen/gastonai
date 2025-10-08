package com.chenweikeng.gastonai;

public class TimeOwner {
    public static int getTime(String rideName) {
        return switch (rideName) {
            case "Alice in Wonderland" -> 206;
            case "Astro Orbitor" -> 88;
            case "Autopia" -> 246;
            case "Big Thunder Mountain Railroad" -> 201;
            case "Buzz Lightyear Astro Blasters" -> 258;
            case "Casey Jr. Circus Train" -> 152;
            case "Chip 'N' Dale's Gadget Coaster" -> 44;
            case "Davy Crockett's Explorer Canoes" -> 150;
            case "Disneyland Monorail" -> 485;
            case "Disneyland Railroad" -> 1025;
            case "Dumbo the Flying Elephant" -> 105;
            case "Enchanted Tiki Room" -> 750;
            case "Finding Nemo Submarine Voyage" -> 790;
            case "Great Moments with Mr. Lincoln" -> 372;
            case "Haunted Mansion Holiday" -> 445;
            case "Indiana Jones Adventure" -> 225;
            case "Jungle Cruise" -> 431;
            case "King Arthur Carrousel" -> 130;
            case "Mad Tea Party" -> 90;
            case "Main Street Carriages" -> 381;
            case "Matterhorn Bobsleds" -> 120;
            case "Mickey & Friends Parking Tram" -> 550;
            case "Mr Toad's Wild Ride" -> 109;
            case "Peoplemover" -> 695;
            case "Peter Pan's Flight" -> 124;
            case "Pinocchio's Daring Journey" -> 168;
            case "Pirates of the Caribbean" -> 841;
            case "Roger Rabbit's Car-Toon Spin" -> 168;
            case "Snow White's Enchanted Wish" -> 122;
            case "Space Mountain" -> 180;
            case "Splash Mountain" -> 484;
            case "Star Wars: Rise of the Resistance" -> 294;
            case "Storybook Land Canal Boats" -> 303;
            case "The Many Adventures of Winnie the Pooh" -> 200;
            case "Tom Sawyer Island Rafts" -> 129;
            case "Golden Zephyr" -> 108;
            case "Goofy's Sky School" -> 91;
            case "Grizzly River Run" -> 361;
            case "Guardians of the Galaxy: Monsters After Dark" -> 123;
            case "Incredicoaster" -> 135;
            case "Inside Out Emotional Whirlwind" -> 98;
            case "Jessie's Critter Carousel" -> 120;
            case "Jumpin' Jellyfish" -> 45;
            case "Luigi's " -> 94;
            case "Mater's Junkyard Jamboree" -> 90;
            case "Monster's Inc. Mike & Sulley to the Rescue!" -> 230;
            case "Pixar Pal-A-Round" -> 547;
            case "Radiator Springs Racers" -> 267;
            case "Red Car Trolley" -> 307;
            case "Silly Symphony Swings" -> 87;
            case "The Little Mermaid: Ariel's Undersea Adventure" -> 369;
            case "Flik's Flyers" -> 98;
            case "Heimlich's Chew Chew Train" -> 102;
            case "The Twilight Zone Tower of Terror" -> 139;
            default -> 99999;
        };
    }
}
