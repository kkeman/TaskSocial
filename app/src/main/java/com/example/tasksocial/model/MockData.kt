package com.example.tasksocial.model

import com.example.tasksocial.viewmodel.MainRepository

class MockData : MainRepository {

    val feedLists = listOf(
        Feed(
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Image_created_with_a_mobile_phone.png/800px-Image_created_with_a_mobile_phone.png",
            description = "using a mobile phone to create an image of the outer world - on the future train track Munich–Berlin, near Essleben (Thuringia, Germany)",
            like = false,
            likecount = 50,
            comments = listOf(Comment("user1", "How to land properly on yacht! Follow TheYachtMogul"),
                Comment("user1", "How to land properly on yacht! Follow TheYachtMogul"),
                Comment("user2", "How to land properly on yacht! Follow TheYachtMogul"),
                Comment("user3", "How to land properly on yacht! Follow TheYachtMogul"),
                Comment("user4", "How to land properly on yacht! Follow TheYachtMogul"),
                Comment("user5", "How to land properly on yacht! Follow TheYachtMogul"),
                Comment("user6", "How to land properly on yacht! Follow TheYachtMogul"),
                Comment("user7", "How to land properly on yacht! Follow TheYachtMogul"),
                Comment("user8", "How to land properly on yacht! Follow TheYachtMogul"),
                Comment("user9", "How to land properly on yacht! Follow TheYachtMogul"), ),
            type = 1,
        ),
        Feed(
            imageUrl = null,
            description = "Jubilee enjoys thoughtful discussions by the campfire.",
            like = false,
            likecount = 500,
            comments = listOf(Comment("the.spooky.joe.2", "Bubble blower was very effective"),
                Comment("alwaysafairchild", "My tortoise does this basically")
            ),
            type = 2,
        ),
        Feed(
            imageUrl = "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg",
            description = "Beezy's favorite past-time is helping you choose your brand color.",
            like = false,
            likecount = 5000,
            comments = listOf(Comment("tytee194", "\"Let me taste u\uD83D\uDE20\"\uD83D\uDE2D\uD83D\uDE2D"),
                Comment("raphael_agneau51", "\uD83D\uDE02That green thinks he has a treat for him ")
            ),
            type = 1,
        ),
        Feed(
            imageUrl = "https://helpx.adobe.com/content/dam/help/en/stock/how-to/visual-reverse-image-search/jcr_content/main-pars/image/visual-reverse-image-search-v2_intro.jpg",
            description = "Mochi is the perfect \"rubbery ducky\" debugging pup, always listening.",
            like = false,
            likecount = 50000,
            comments = listOf(Comment("therealsixik", "@rainwaterimages the way he gets rid of him hahaha"),
                Comment("gisele_leite1609", "quero isso @thiago120789 kkkkk")
            ),
            type = 1,
        ),
        Feed(
            imageUrl = null,
            description = "Brewery loves fetching you your favorite homebrew.",
            like = false,
            likecount = 0,
            comments = listOf(),
            type = 2,
        ),
        Feed(
            imageUrl = null,
            description = "Picture yourself in a boat on a river, Lucy is a pup with kaleidoscope eyes.",
            like = false,
            likecount = 10,
            comments = emptyList(),
            type = 2,
        ),
        Feed(
            imageUrl = null,
            description = "Is it a bird? A plane? No, it's Astro blasting off into your heart!",
            like = false,
            likecount = 20,
            comments = null,
            type = 2,
        ),
        Feed(
            imageUrl = "https://interactive-examples.mdn.mozilla.net/media/cc0-images/grapefruit-slice-332-332.jpg",
            description = "Boo is just a teddy bear in disguise. What he lacks in grace, he makes up in charm.",
            like = false,
            likecount = 30,
            comments = listOf(Comment("spencer_og_nla", "This is the English comment you've been looking for \uD83D\uDE02 isn't that aqua man?"),
                Comment("spencer_og_nla", "Dude, I KNOW how to peddle already!")
            ),
            type = 1,
        ),
        Feed(
            imageUrl = null,
            description = "Pippa likes to look out the window and write pup-poetry.",
            like = true,
            likecount = 40,
            comments = listOf(Comment("patricktumbarello", "They also share a Facebook page “Jennifer&Edward”")),
            type = 2,
        ),
        Feed(
            imageUrl = null,
            description = "Coco enjoys getting pampered at the local puppy spa.",
            like = false,
            likecount = 50,
            comments = listOf(Comment("seadog129", "The only way to work out \uD83D\uDCAA")),
            type = 2,
        ),
        Feed(
            imageUrl = null,
            description = "Brody is a good boy, waiting for your next command.",
            like = false,
            likecount = 60,
            comments = listOf(Comment("donnab11_12", "It takes two to tango")),
            type = 2,
        ),
        Feed(
            imageUrl = "https://images.unsplash.com/photo-1453728013993-6d66e9c9123a?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTF8fGNoYW5nZXxlbnwwfHwwfHw%3D&w=1000&q=80",
            description = "Stella! Calm and always up for a challenge, she's the perfect companion.",
            like = false,
            likecount = 70,
            comments = listOf(Comment("prensborsaci", "Yes Morning....\uD83D\uDE4A"),
                Comment("beautifulhousewife", "I’m going to get you!!!")
            ),
            type = 1,
        ),
    )

    val adLists = listOf(
        Ad(
            title = "Ad Title1"
        ),
        Ad(
            title = "Ad Title2"
        ),
        Ad(
            title = "Ad Title3"
        ),
        Ad(
            title = "Ad Title4"
        ),
        Ad(
            title = "Ad Title5"
        ),
        Ad(
            title = "Ad Title6"
        ),
    )

    override fun getModels(): List<Any> {
        val allCount = feedLists.count() / 3 + feedLists.count()
        val allList: MutableList<Any> = feedLists.toMutableList()
        var j = -1

        for (i in 2..allCount step 3) {
            if (adLists.size > ++j)
                allList.add(i, adLists[j])
        }
        return allList
    }
}