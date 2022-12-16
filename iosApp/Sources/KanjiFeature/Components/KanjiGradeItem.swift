//
//  KanjiGradeItem.swift
//  
//
//  Created by surajsau on 2022/12/11.
//

import SwiftUI

struct KanjiGradeItem: View {

    let grade: String

    var body: some View {
        ZStack {
            Text(self.grade)
                .font(.title3)
                .fontWeight(.semibold)
        }
        .frame(width: 72, height: 72)
    }
}

struct AllSchoolItem: View {

    var body: some View {
        ZStack {
            Text("All school")
                .font(.title3)
                .fontWeight(.semibold)
                .frame(maxWidth: .infinity)
        }
        .padding()
    }
}

struct KanjiGradeItem_Previews: PreviewProvider {
    static var previews: some View {
        ZStack {
            VStack {
                Spacer()

                HStack {
                    Spacer()

                    KanjiGradeItem(grade: "6")

                    Spacer()

                    KanjiGradeItem(grade: "7")

                    Spacer()
                }
                .padding()

                AllSchoolItem()

                Spacer()
            }
            .padding()
        }
    }
}
